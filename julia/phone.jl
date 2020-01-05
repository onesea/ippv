#!/usr/local/bin/julia

mutable struct Node{T}
    data::T
    iso::String
    child::Vector{Union{Nothing,Node{T}}}
    Node{T}(data,iso) where {T} =(y=new(); y.data=data; y.iso=iso; y.child=Vector{Union{Nothing,Node{T}}}(nothing,10); y)
end

TREE=Node{Int}(0,"root")

function add(cc::String, iso::String)
   root = TREE 
   i::Int = 0
   for ch in cc
    index = ch - '0' + 1
    node = root.child[index]
    if node === nothing
        root.child[index] = Node{Int}(0,"")
        node = root.child[index]
    end
    i+=1
    if i == length(cc)
        node.data = 1
        node.iso = iso
    else
        root = node
    end
   end 
end

function parse(phone::String)
    len::Int = 0
    ld::Int = 0
    ac::Int = 0
    iso::String=""
    root = TREE 
    i::Int = 0
    for ch in phone
     index = ch - '0' + 1
     node = root.child[index]
     if node === nothing
        break
     end
     i += 1
     if node.data == 1
        if len == 0 len = i
        elseif ld == 0 ld = i
        else ac = i
        end
        iso = node.iso
     end
     root = node
    end

    len,ld,ac,iso
end

add("86","CHN")
add("86186","CU")
add("86180","CT")
add("1","USA")
add("1186","USCU")
add("1180","USCT")
add("11809","US-9")
println(parse("8610801111"))
println(parse("861801111"))
println(parse("12801111"))
println(parse("11801111"))
println(parse("118097777"))