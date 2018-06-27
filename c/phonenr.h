#ifndef _PHONE_NR_H_
#define _PHONE_NR_H_

#ifdef __cplusplus
extern "C" {
#endif

int cc_init();
int cc_match(const char *nr);
// optional, if not called, built-in list will be used
void cc_add(const char *cc);
int parse_phone_nr(char *phone, char *default_cc);

#ifdef __cplusplus
}
#endif // cplusplus

#endif //_PHONE_NR_H_
