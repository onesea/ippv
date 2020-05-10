#!/usr/bin/env lua

require('phone')

parser = ippv:new(nil)
function parse (no)
	return coroutine.yield(no,parser:parse(no))
end

phone_list={'123', '12390', '12301', '12301121', '12302', '79311200'}
co = coroutine.create(function (index,options)
		for i, no in ipairs(phone_list) do
			index,options = parse(no)
		end
	end)

parser:add('123','my')
parser:add('12301','up')
parser:add('123011','co')

res = true
while res do
	res,no,cc,ld,ac,iso =  coroutine.resume(co, 1, 'test')
	if res then print(res,cc,ld,ac,iso,no) end
end
