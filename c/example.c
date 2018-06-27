#include "phonenr.h"

#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <string.h>

int main(int argc, char *argv[]) {
	if (argc > 1 && strcmp(argv[1], "-s") == 0) {
		cc_init(); // only needed when using core algrithm directly
		for (int i = 0; i < 100000000; ++i) {
			int len = cc_match("85212345678");
			if (len != 3) {
				printf("error %d\n", len);
				break;
			}
		}

		exit(0);
	}

	if (argc < 3) {
		printf("\tusage: %s country-code phone-nr\n", argv[0]);
		exit(1);
	}

	printf("country=[%s], phone=[%s] ==> ", argv[1], argv[2]);

	char country[32];
	strncpy(country, argv[1], 31);
	char phone[64];
	strncpy(phone, argv[2], 63);
	int ret = parse_phone_nr(phone, country);
	if (ret == 0)
		printf("[%s] [%s]\n", country, phone);
	else
		printf("inalid phone number\n");
}
