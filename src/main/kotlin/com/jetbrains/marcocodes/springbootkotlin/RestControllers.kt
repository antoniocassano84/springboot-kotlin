package com.jetbrains.marcocodes.springbootkotlin

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/v1/articles")
class ArticleController {

    val articles = mutableListOf(Article("My Title", "my content"))

    @GetMapping
    fun articles() = articles

    @GetMapping("/{slug}")
    fun article(@PathVariable slug: String) =
            articles.find { article -> article.slug == slug } ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)

    @PostMapping
    fun newArticle(@RequestBody article: Article): Article {
        articles.add(article)
        return article
    }

    @PutMapping("/{slug}")
    fun updateArticle(@RequestBody article: Article, @PathVariable slug: String): Article {
        val existingArticle = articles.find { it.slug == slug } ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
        existingArticle.content = article.content
        return article
    }

    @DeleteMapping("/{slug}")
    fun removeArticle(@PathVariable slug: String) {
        articles.removeIf { article -> article.slug == slug }
    }

}