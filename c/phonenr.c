#include "phonenr.h"
#include <stdlib.h>

/*---------------------------- core algrithm -----------------------------------*/
static void *g_tree[] = {0,0,0,0,0,0,0,0,0,0}; 
// add a country code, i.e. build a parsing tree
void cc_add(const char *cc) {
	void **tree = g_tree;
	while (*cc) {
		int n = *cc++ - '0';
		void **node = (void**)tree[n];
		if (node == 0) {
			node = (void**)malloc(sizeof (void*) * 10);
			for (int i = 0; i < 10; ++i)
				node[i] = 0;
			tree[n] = node;
		}
		tree = node;
	}
}

// country code matching，returns the length of a successful match, or -1 on failure
int cc_match(const char *nr) {
	void **tree = g_tree;
	for (int i = 0; ; ++i) {
		void **node = 0;
		if (*nr) {
			int n = *nr++ - '0';
			node = (void**)tree[n];
		}

		if (node == 0) {
			for (int j = 0; j < 10; ++j)
				if (tree[j] != 0)
					return -1;
			return i;
		}

		tree = node;
	}
	return 0;
}

int cc_init() {
#include "country_dialing_code.h"
	for (unsigned i = 0; i < sizeof cc_list / sizeof cc_list[0]; ++i) {
		cc_add(cc_list[i]);
	}
	return 0;
}

/*------------------ core algrithm ends，below are api wrappers -------------------------*/
// parse and validate country code and phone number, as well as filtering white space chars
int parse_phone_nr(char *phone, char *cc) {
	if (g_tree[1] == 0) {
#include "country_dialing_code.h"
		for (unsigned i = 0; i < sizeof cc_list / sizeof cc_list[0]; ++i) {
			cc_add(cc_list[i]);
		}
	}
	char buf[100];
	int n = 0;
	int plus = 0;
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
