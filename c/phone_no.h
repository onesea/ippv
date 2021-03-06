#ifndef _PHONE_NO_H_
#define _PHONE_NO_H_

#ifdef __cplusplus
extern "C" {
#endif

int cc_init();
int cc_match(const char *no);
int cc_match_2(const char *no, int *ld, char **iso);
int cc_match_3(const char *no, int *ld, int *ac, char **iso);
// optional, if not called, built-in list will be used
void cc_add(const char *cc, const char *iso);

#ifdef __cplusplus
}
#endif // cplusplus

#endif //_PHONE_NO_H_
