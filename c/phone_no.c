#include "phone_no.h"
#include <stdlib.h>
#include <string.h>

/*---------------------------- core algrithm -----------------------------------*/
typedef struct __node_st {
	int type_;
	char iso_[4];
	struct __node_st *child_;
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
			strncpy(node->iso_, iso, sizeof node->iso_);
			break;
		}
		if (node->type_ == 0)
			node->type_ = 1;
		if (node->child_ != 0) {
			tree = node->child_;
			continue;
		}
		node = (node_t*)malloc(sizeof (node_t) * 10);
		for (int i = 0; i < 10; ++i) {
			node[i].type_ = 0;
			node[i].child_ = 0;
		}
		tree[d].child_ = node;
		tree = node;
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

int cc_match_ld(const char *no, int *ld, char **iso) {
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
			*iso = node->iso_;
			if (node->child_ == 0)
				break;
		}

		tree = node->child_;
	}
	return len;
}

int cc_init() {
	const char *cc_list[] = {
#include "../country_code.txt"
	};
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
