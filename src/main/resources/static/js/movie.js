const urlParams = new URLSearchParams(window.location.search);
const movieId = urlParams.get("id");
const jwt = sessionStorage.getItem("token");

if (!movieId) {
    document.getElementById("title").textContent = "ID фильма не указан в URL";
} else {
    fetch(`/movie/${movieId}`, {
        method: 'GET',
        headers: {
            "Authorization": "Bearer " + jwt
        }
    })
        .then(response => {
            if (!response.ok) throw new Error("Фильм не найден");
            return response.json();
        })
        .then(data => {
            document.getElementById("title").textContent = data.title;
            document.getElementById("year").textContent = "Год выпуска: " + data.year;
            document.getElementById("description").textContent = data.description;
            document.getElementById("poster").src = data.posterUrl || "/static/images/no-image.png";

            let sum = 0;
            data.reviews.forEach(r => sum += r.rating);
            const meanRating = (data.reviews.length > 0)
                ? (sum / data.reviews.length).toFixed(2)
                : "Нет оценок";

            document.getElementById("meanRating").textContent = "⭐ Средняя оценка: " + meanRating;

            const reviewsList = document.getElementById("reviewsList");
            data.reviews.forEach(review => {
                const div = document.createElement("div");
                div.className = "review";
                div.innerHTML = `
                <span>Оценка ${review.rating}</span>
                <span>Комментарий: ${review.text}</span>
            `;
                reviewsList.appendChild(div);
            });
        })
        .catch(error => {
            document.getElementById("title").textContent = "Ошибка загрузки фильма 😢";
            console.error("Ошибка:", error);
        });
}

fetch('/fragments/header.html')
    .then(response => response.text())
    .then(html => {
        document.getElementById('header-placeholder').innerHTML = html;
    })
    .catch(error => {
        console.error('Не удалось загрузить header:', error);
    });

// Добавление отзыва
const submitReviewBtn = document.getElementById('submitReviewBtn');
submitReviewBtn.addEventListener('click', () => {
    const rating = parseInt(document.getElementById('ratingInput').value);
    const comment = document.getElementById('commentInput').value.trim();

    if (isNaN(rating) || rating < 1 || rating > 10) {
        alert("Пожалуйста, укажите оценку от 1 до 10");
        return;
    }

    fetch(`/review/create/${movieId}`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Authorization": "Bearer " + jwt
        },
        body: JSON.stringify({
            rating: rating,
            text: comment
        })
    })
        .then(response => {
            if (response.ok) {
                alert("Отзыв добавлен!");
                location.reload();
            } else {
                alert("Ошибка добавления отзыва");
            }
        })
        .catch(error => {
            console.error("Ошибка при отправке отзыва:", error);
            alert("Ошибка сети или авторизации");
        });
});
