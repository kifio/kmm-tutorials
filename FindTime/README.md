# kmm-find-time
Приложение из первых пяти глав книги [Kotlin Multiplatform by Tutorials](https://www.raywenderlich.com/books/kotlin-multiplatform-by-tutorials/v1.0).<br />
Предназначено для поиска времени, на которое можно поставить совещание между коллегами находящимися в разных часовых поясах.<br />
В приложении реализуются базовые вещи, которые можно сделать с помощью `Kotlin Multiplatform`:
 - Бизнес логика в модуле `shared`
 - Общий UI для Android/Desktop в модуле `shared-ui`
 - GUI для iOS приложения на SwfitUI в модуле `iosApp`

Для компиляции дотаточно jre11. Для сборки desktop дистрибутива необходим jdk18.
