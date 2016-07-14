package com.iii.fogal

import java.util.List;

import grails.transaction.Transactional

@Transactional
class UtilityService {

	public Integer getRandom(List objList) {
		Integer numEls = objList.size()
		Integer numRan = Math.random()
		def rnd = new Random().nextInt(objList.size())
	}
	
}
