package com.paulo.springkotlin.controllers

import com.paulo.springkotlin.entities.Contact
import com.paulo.springkotlin.repositories.ContactRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/contacts")
class ContactController {

    @Autowired
    lateinit var contactRepository: ContactRepository

    @GetMapping
    fun list () : List<Contact> {
        return contactRepository.findAll()
    }

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long) : Contact {
        return contactRepository.findById(id).orElseThrow{ ResponseStatusException(HttpStatus.NOT_FOUND, "Contact not found.") }
    }

    @PostMapping
    fun create (@RequestBody contact: Contact) : Contact {
        return contactRepository.save(contact)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody contact: Contact) : Contact {
        val contactDb = contactRepository.findById(id).orElseThrow{ ResponseStatusException(HttpStatus.NOT_FOUND, "Contact not found.") }

        contactDb.apply {
            this.name = contact.name
            this.email = contact.email
        }

        return contactRepository.save(contactDb)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) {
        val contact = contactRepository.findById(id).orElseThrow{ ResponseStatusException(HttpStatus.NOT_FOUND, "Contact not found.") }
        contactRepository.delete(contact)
    }
}