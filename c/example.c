#include "phone_no.h"

#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <string.h>

int main(int argc, char *argv[])
{
	if (argc < 2) {
		printf("\tusage: %s phone-no\n", argv[0]);
		exit(1);
	}

	cc_init();

	char phone[50];
	strncpy(phone, argv[1][0]=='+'?argv[1]+1:argv[1], sizeof phone);
	printf("%s: ", phone);

	int ld=0;
	int ac=0;
	char *iso=NULL;
	int len = cc_match_3(phone,&ld,&ac,&iso);
	if (len <= 0) {
		printf("parse error\n");
		exit(1);
	}

	int t = phone[len];
	phone[len] = 0;
	printf("%s", phone);
	phone[len] = t;
	if (ld <= 0) {
		printf(" %s %s\n", phone+len, iso);
		exit(0);
	}

	t = phone[ld];
	phone[ld] = 0;
	printf(" %s", phone+len);
	phone[ld] = t;
	if (ac<=0) {
		printf(" %s %s\n", phone+ld,iso);
		exit(0);
	}

	t = phone[ac];
	phone[ac] = 0;
	printf(" %s", phone+ld);
	phone[ac] = t;
	printf(" %s %s\n", phone+ac,iso);

	// stress test
	for (int i=0; i<100000000;++i)
		len = cc_match_3(phone,&ld,&ac,&iso);
}
