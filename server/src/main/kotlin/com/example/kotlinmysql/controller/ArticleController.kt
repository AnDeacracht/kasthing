package com.example.kotlinmysql.controller

import com.example.kotlinmysql.model.Article
import com.example.kotlinmysql.repository.ArticleRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@CrossOrigin(origins = ["http://localhost:4200"], maxAge = 3600)
@RestController
@RequestMapping("/api")
class ArticleController(private val articleRepository: ArticleRepository) {

    @GetMapping("/articles")
    fun getAllArticles(): List<Article> =
            articleRepository.findAll()

    @GetMapping("/articles/{id}")
    fun findById(@PathVariable(value = "id") articleId: Long): List<Article> {
        val result = articleRepository.findById(articleId)
        return if (result.isPresent) listOf(result.get()) else emptyList() //whoop
    }

    @PostMapping("/articles")
    fun createNewArticle(@Valid @RequestBody article: Article): Article =
            articleRepository.save(article)

    @PutMapping("/articles/update/{id}")
    fun updateArticleById(@PathVariable(value = "id") articleId: Long,
                          @Valid @RequestBody newArticle: Article): ResponseEntity<Article> {

        return articleRepository.findById(articleId).map { existingArticle ->
            val updatedArticle: Article = existingArticle.copy(
                    title = newArticle.title,
                    content = newArticle.content
            )
            ResponseEntity.ok().body(articleRepository.save(updatedArticle))
        }.orElse(ResponseEntity.notFound().build())
    }

    @DeleteMapping("/articles/{id}")
    fun deleteArticleById(@PathVariable(value = "id") articleId: Long): ResponseEntity<Void> {

        return articleRepository.findById(articleId).map { article ->
            articleRepository.delete(article)
            ResponseEntity<Void>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())
    }
}

