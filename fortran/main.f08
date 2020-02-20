program test
interface
    function parse()
    end
    function add_cc(cc,iso) result(res)
        character(30) :: cc
        character(3)  :: iso
    end
end interface
    integer :: res
    character(3) :: ret
    character(11) :: msg
    character(30) :: cc = "1234567"
    call s()
    res = add_cc(cc,"ISO")
    ret = f1(msg)
    write (*,*) msg,',', ret,parse()
contains
    function f1(o)
        character(3) :: f1
        character(11),intent(out) :: o
        o = "hello world"
        f1 = "abc"
    end
    subroutine s()
        write (*,*) "in s()"
        return
    end
end
