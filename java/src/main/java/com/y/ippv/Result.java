package com.y.ippv;

public class Result {
	public int cc_ = 0;
	public int ld_ = 0;
	public int ac_ = 0;
	public String iso_ = null;
	public String toString() {
		return String.format("{cc:%d,ld:%d,ac:%d,iso:%s}",cc_,ld_,ac_,iso_);
	}
}
