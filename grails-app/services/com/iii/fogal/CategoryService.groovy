package com.iii.fogal

import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.Files

import org.apache.commons.io.FileDeleteStrategy

class CategoryService {
	
	def grailsApplication
	def galleryService

	def addCategoryToFileSystem(String cPath) {
		try {
			String baseDir = grailsApplication.config.file.upload.directory
			Path categoryPath = Paths.get(baseDir, cPath)
			File categoryDir = categoryPath.toFile()
			categoryDir.mkdir()
		} catch (Exception e) {
			log.debug("addGalleryToCategory: ${e}")
			throw e
		}
	}
	
	def deleteCategoryFromFileSystem(Category category) {
		log.debug "delete category from file system (1)"
		for (Gallery gallery : category?.galleries) {
			galleryService.deleteGalleryFromCategory(gallery, category)
		}
		String baseDir = grailsApplication.config.file.upload.directory
		Path categoryPath = Paths.get(baseDir, category.path)
		File categoryDir = categoryPath.toFile()
		System.gc()
		FileDeleteStrategy.FORCE.delete(categoryDir)
	}
	
	Boolean updateCategoryOnFileSystem(Category category, String newPath) {
		Boolean success = false
		try {
			String baseDir = grailsApplication.config.file.upload.directory
			Path categoryPath = Paths.get(baseDir, category.path)
			File categoryDir = categoryPath.toFile()
			log.debug "categoryDir: ${categoryDir} (writeable: ${categoryDir.canWrite()})"
			Path categoryPathNew = Paths.get(baseDir, newPath)
			File categoryDirNew = categoryPathNew.toFile()
			log.debug "categoryDirNew: ${categoryDirNew} (readable: ${categoryDirNew.canRead()})"
			success = categoryDir.renameTo(categoryDirNew)
			log.debug "categoryDir: ${categoryDir} (success: ${success})"
		} catch (Exception e) {
			log.debug("updateCategoryOnFileSystem: ${e}")
		} finally {
			return success
		}
	}

}
