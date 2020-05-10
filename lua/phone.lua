
ippv = {child={}}

function ippv:new(o)
	o = o or {child={}}
	setmetatable(o, self)
	self.__index = self
	return o
end

function ippv:add(cc,iso)
	node = self
	for i = 1, string.len(cc) do
		index=string.byte(cc,i) - string.byte('0',1)
		if not node.child[index] then
			node.child[index] = {child={}}
		end
		node = node.child[index]
	end
	node.value = 1
	node.iso = iso
end

function ippv:parse(no)
	local node = self
	local cc = nil
	local ld = nil
	local ac = nil
	local iso = nil
	for i = 1, string.len(no) do
		index=string.byte(no,i) - string.byte('0',1)
		node = node.child[index]
		if not node then
			break
		end
		if node.value == nil or not node.value == 1 then
			goto continue
		end
		if not cc then
			cc = i
		elseif not ld then
			ld = i
		else
			ac = i
		end
		iso = node.iso
		::continue::
	end
	return cc,ld,ac,iso
end
