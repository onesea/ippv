#include "phone_no.h"
#include <stdlib.h>
#include <string.h>

/*---------------------------- core algrithm -----------------------------------*/
typedef struct node_st {
	int type_;
	char iso_[4];
	struct node_st *child_;
} node_t;

static node_t *g_tree = 0; 
// add a country code, i.e. build a parsing tree
void cc_add(const char *cc, const char *iso) {
	node_t *tree = g_tree;
	while (1) {
		int d = *cc++ - '0';
		node_t *node = tree+d;
		if (*cc == 0) {
			node->type_ = 2;
			strcpy(node->iso_, iso);
			break;
		}
		if (node->type_ == 0)
			node->type_ = 1;
		if (node->child_ != 0) {
			tree = node->child_;
		} else {
			node = (node_t*)malloc(sizeof (node_t) * 10);
			for (int i = 0; i < 10; ++i) {
				node[i].type_ = 0;
				node[i].child_ = 0;
			}
			tree[d].child_ = node;
			tree = node;
		}
	}
}

// country code matching，returns the length of a successful match, or -1 on failure
int cc_match(const char *nr) {
	node_t *tree = g_tree;
	for (int i = 0; *nr; ++i) {
		int d = *nr++ - '0';
		node_t *node = tree + d;

		if (node->type_ == 0) {
			return 0;
		}

		if (node->type_ == 2) {
			return i+1;
		}

		tree = node->child_;
	}
	return 0;
}

int cc_match_ld(const char *no, int *ld, char *iso) {
	int len = 0;
	*ld = 0;
	node_t *tree = g_tree;
	for (int i = 0; *no; ++i) {
		int d = *no++ - '0';
		node_t *node = tree + d;

		if (node->type_ == 0) {
			break;
		}

		if (node->type_ == 2) {
			if (len == 0)
				len = i+1;
			else
				*ld = i+1;
			iso[0]=node->iso_[0];
			iso[1]=node->iso_[1];
			iso[2]=node->iso_[2];
			iso[3]=0;
			if (node->child_ == 0)
				break;
		}

		tree = node->child_;
	}
	return len;
}

int cc_init() {
#include "country_code.h"
	g_tree = (node_t*)malloc(sizeof (node_t) * 10);
	for (int i = 0; i < 10; ++i) {
		g_tree[i].type_ = 0;
		g_tree[i].child_ = 0;
	}
	for (unsigned i = 0; i < sizeof cc_list / sizeof cc_list[0]; i+=2) {
		cc_add(cc_list[i], cc_list[i+1]);
	}
	return 0;
}

/*------------------ core algrithm ends，below are api wrappers -------------------------*/
int parse_phone_no(char *phone, char *cc) {
	if (g_tree == 0) {
#include "country_code.h"
		g_tree = (node_t*)malloc(sizeof (node_t) * 10);
		for (int i = 0; i < 10; ++i) {
			g_tree[i].type_ = 0;
			g_tree[i].child_ = 0;
		}
		for (unsigned i = 0; i < sizeof cc_list / sizeof cc_list[0]; i+=2) {
			cc_add(cc_list[i], cc_list[i+1]);
		}
	}

	int n = 0;
	int plus = 0;
	char buf[100];
	// 过滤空白字符
	for (int i = 0; phone[i]; ++i) {
		if (phone[i] != ' ' && phone[i] != '-') {
			if (phone[i] != '+')
				buf[n++] = phone[i];
			else if (n == 0)
				plus = 1;
		}
	}

	buf[n] = 0;
	if (plus > 0) {
		// 有+号，提取国家码
		int len = cc_match(buf);
		if (len <= 0)
			return -1;

		int i = 0;
		*cc++ = '+';
		for ( ; i < len; ++i)
			*cc++ = buf[i];
		*cc = 0;

		for ( ; buf[i]; ++i) {
			*phone++ = buf[i];
		}
		*phone = 0;
	} else {
		// 不带+号，号码不变
		char *p = buf;
		while ((*phone++ = *p++) != 0) ;
		// 区号过滤空白字符
		n = 0;
		for (int i = 0; cc[i]; ++i) {
			if (cc[i] != ' ')
				buf[n++] = cc[i];
		}
		if (n == 0 || (n == 1 && buf[0] == '+'))
			return -1;
		if (buf[0] != '+')
			*cc++ = '+';
		for (int i = 0; i < n; ++i)
			*cc++ = buf[i];
		*cc = 0;
	}

	return 0;
}
