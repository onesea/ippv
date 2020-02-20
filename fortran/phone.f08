function parse() result(res)
        res = 99999;
end
function add_cc(cc,iso) result(res)
        character(30) :: cc
        character(3)  :: iso
        write (*,*) "adding ",cc,": ",iso
        res = 10000;
end
