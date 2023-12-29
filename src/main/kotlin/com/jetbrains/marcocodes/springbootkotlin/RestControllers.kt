package com.jetbrains.marcocodes.springbootkotlin

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/article")
class ArticleController {

    val articles = mutableListOf(Article("My Title", "my content"))

    @GetMapping
    fun articles() = articles

}