package com.hybernet.classwork

import jakarta.persistence.EntityManager
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.assertEquals

@SpringBootTest
class ClassworkApplicationTests {

	@Autowired
	lateinit var entityManager: EntityManager

	@Test
	fun user() {
		// Create a new User entity
		val user = User(
			name = "chandra",
			email = "chandra@chandra.com"
		)

		// Persist the entity
		entityManager.persist(user)

		// Flush and clear the persistence context to ensure retrieval from the database
		entityManager.flush()
		entityManager.clear()

		// Retrieve the entity
		val retrievedUser = entityManager.find(User::class.java, user.id)

		// Assert specific fields to avoid proxy comparison issues
		assertEquals(user.name, retrievedUser?.name)
		assertEquals(user.email, retrievedUser?.email)
	}
}
