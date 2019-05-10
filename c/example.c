#include "phone_no.h"

#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <string.h>

void stress_test() {
	cc_init(); // only needed when using core algrithm directly
	int len, ld;
	char iso[8];
	for (int i = 0; i < 100000000; ++i) {
		//len = cc_match("390669845678");
		len = cc_match_ld("390669845678", &ld, iso);
		if (len != 2) {
			printf("error %d\n", len);
			break;
		}
	}
}

void general_test(int argc, char *argv[]) {
	if (argc < 3) {
		printf("\tusage: %s country-code phone-no\n", argv[0]);
		exit(1);
	}

	printf("country=[%s], phone=[%s] -> ", argv[1], argv[2]);

	char country[32];
	strncpy(country, argv[1], 31);
	char phone[64];
	strncpy(phone, argv[2], 63);

	int ret = parse_phone_no(phone, country);
	if (ret != 0) {
		printf("invalid phone number\n");
		return;
	}
	
	printf("[%s] [%s]\n", country, phone);
	char buf[120];
	snprintf(buf, sizeof buf, "%s%s", country+1,phone);
	int ld;
	char iso[8];
	int len = cc_match_ld(buf,&ld,iso);
	if (len != strlen(country)-1) {
		printf("error parse leading digits\n");
	} else {
		if (ld > 0) {
			int t = buf[ld];
			buf[ld] = 0;
			printf("alt: %s ", buf);
			buf[ld] = t;
			printf("%s %s\n", buf+ld,iso);
		}
	}
}

int main(int argc, char *argv[]) {
	if (argc > 1 && strcmp(argv[1], "-s") == 0) {
		stress_test();
	} else {
		general_test(argc, argv);
	}

}
