databaseChangeLog = {

	changeSet(author: "Margit (generated)", id: "1486520697601-1") {
		createTable(tableName: "category") {
			column(name: "id", type: "int8") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "categoryPK")
			}

			column(name: "version", type: "int8") {
				constraints(nullable: "false")
			}

			column(name: "description", type: "varchar(255)")

			column(name: "home_page_tile", type: "varchar(255)")

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "path", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Margit (generated)", id: "1486520697601-2") {
		createTable(tableName: "gallery") {
			column(name: "id", type: "int8") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "galleryPK")
			}

			column(name: "version", type: "int8") {
				constraints(nullable: "false")
			}

			column(name: "category_id", type: "int8") {
				constraints(nullable: "false")
			}

			column(name: "description", type: "varchar(255)")

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "path", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "galleries_idx", type: "int4")
		}
	}

	changeSet(author: "Margit (generated)", id: "1486520697601-3") {
		createTable(tableName: "link") {
			column(name: "id", type: "int8") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "linkPK")
			}

			column(name: "version", type: "int8") {
				constraints(nullable: "false")
			}

			column(name: "link_cat", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "link_href", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "link_text", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Margit (generated)", id: "1486520697601-4") {
		createTable(tableName: "photo") {
			column(name: "id", type: "int8") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "photoPK")
			}

			column(name: "version", type: "int8") {
				constraints(nullable: "false")
			}

			column(name: "aspect", type: "varchar(255)")

			column(name: "copyright", type: "varchar(255)")

			column(name: "credit", type: "varchar(255)")

			column(name: "description", type: "varchar(2048)")

			column(name: "file_size", type: "int8")

			column(name: "gallery_id", type: "int8") {
				constraints(nullable: "false")
			}

			column(name: "gallery_idx", type: "int4")

			column(name: "image", type: "bytea")

			column(name: "image_height", type: "int8")

			column(name: "image_width", type: "int8")

			column(name: "location", type: "varchar(255)")

			column(name: "original_filename", type: "varchar(255)")

			column(name: "photo_date", type: "varchar(255)")

			column(name: "thumbnail_filename", type: "varchar(255)")

			column(name: "title", type: "varchar(255)")

			column(name: "photos_idx", type: "int4")
		}
	}

	changeSet(author: "Margit (generated)", id: "1486520697601-5") {
		createTable(tableName: "resource") {
			column(name: "id", type: "int8") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "resourcePK")
			}

			column(name: "version", type: "int8") {
				constraints(nullable: "false")
			}

			column(name: "description", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "group_name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "link_href", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "link_text", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "title", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Margit (generated)", id: "1486520697601-6") {
		createTable(tableName: "role") {
			column(name: "id", type: "int8") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "rolePK")
			}

			column(name: "version", type: "int8") {
				constraints(nullable: "false")
			}

			column(name: "authority", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Margit (generated)", id: "1486520697601-7") {
		createTable(tableName: "User") {
			column(name: "id", type: "int8") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "UserPK")
			}

			column(name: "version", type: "int8") {
				constraints(nullable: "false")
			}

			column(name: "account_expired", type: "boolean") {
				constraints(nullable: "false")
			}

			column(name: "account_locked", type: "boolean") {
				constraints(nullable: "false")
			}

			column(name: "enabled", type: "boolean") {
				constraints(nullable: "false")
			}

			column(name: "password", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "password_expired", type: "boolean") {
				constraints(nullable: "false")
			}

			column(name: "username", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Margit (generated)", id: "1486520697601-8") {
		createTable(tableName: "user_role") {
			column(name: "role_id", type: "int8") {
				constraints(nullable: "false")
			}

			column(name: "user_id", type: "int8") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Margit (generated)", id: "1486520697601-9") {
		addPrimaryKey(columnNames: "role_id, user_id", constraintName: "user_rolePK", tableName: "user_role")
	}

	changeSet(author: "Margit (generated)", id: "1486520697601-14") {
		createIndex(indexName: "username_uniq_1486520697421", tableName: "User", unique: "true") {
			column(name: "username")
		}
	}

	changeSet(author: "Margit (generated)", id: "1486520697601-15") {
		createIndex(indexName: "path_uniq_1486520697426", tableName: "category", unique: "true") {
			column(name: "path")
		}
	}

	changeSet(author: "Margit (generated)", id: "1486520697601-16") {
		createIndex(indexName: "unique_path", tableName: "gallery", unique: "true") {
			column(name: "category_id")

			column(name: "path")
		}
	}

	changeSet(author: "Margit (generated)", id: "1486520697601-17") {
		createIndex(indexName: "authority_uniq_1486520697459", tableName: "role", unique: "true") {
			column(name: "authority")
		}
	}

	changeSet(author: "Margit (generated)", id: "1486520697601-18") {
		createSequence(sequenceName: "hibernate_sequence")
	}

	changeSet(author: "Margit (generated)", id: "1486520697601-10") {
		addForeignKeyConstraint(baseColumnNames: "category_id", baseTableName: "gallery", constraintName: "FK_4kfwrlpmtdho6slyl314hmkgj", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "category", referencesUniqueColumn: "false")
	}

	changeSet(author: "Margit (generated)", id: "1486520697601-11") {
		addForeignKeyConstraint(baseColumnNames: "gallery_id", baseTableName: "photo", constraintName: "FK_tnc6ldto2ggd6xdgjt4iy67rh", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "gallery", referencesUniqueColumn: "false")
	}

	changeSet(author: "Margit (generated)", id: "1486520697601-12") {
		addForeignKeyConstraint(baseColumnNames: "role_id", baseTableName: "user_role", constraintName: "FK_it77eq964jhfqtu54081ebtio", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "role", referencesUniqueColumn: "false")
	}

	changeSet(author: "Margit (generated)", id: "1486520697601-13") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "user_role", constraintName: "FK_apcc8lxk2xnug8377fatvbn04", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "User", referencesUniqueColumn: "false")
	}
}
