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
class ArticleController(val articleRepository : ArticleRepository) {

    @GetMapping
    fun articles() = articleRepository.findAllByOrderByCreatedAtDesc()

    @GetMapping("/{slug}")
    fun article(@PathVariable slug: String) =
            articleRepository.findBySlug(slug).orElseThrow { throw ResponseStatusException(HttpStatus.NOT_FOUND) }

    @PostMapping
    fun newArticle(@RequestBody article: Article): Article {
        article.id = null
        articleRepository.save(article)
        return article
    }

    @PutMapping("/{slug}")
    fun updateArticle(@RequestBody article: Article, @PathVariable slug: String): Article {
        val existingArticle =
                articleRepository.findBySlug(slug).orElseThrow { throw ResponseStatusException(HttpStatus.NOT_FOUND) }
        existingArticle.content = article.content
        articleRepository.save(existingArticle)
        return article
    }

    @DeleteMapping("/{slug}")
    fun removeArticle(@PathVariable slug: String) {
        val existingArticle =
                articleRepository.findBySlug(slug).orElseThrow { throw ResponseStatusException(HttpStatus.NOT_FOUND) }
        articleRepository.delete(existingArticle)
    }

}