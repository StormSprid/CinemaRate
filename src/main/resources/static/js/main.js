let currentPage = 0; // нумерация с 0
const pageSize = 6;
let totalPages = 1;

function fetchAndRenderPage(page) {
    ///movie/all/page?page=${page}&size=${pageSize}
    fetch(`/movie/for-user`)
        .then(response => {
            if (!response.ok) throw new Error("Ошибка загрузки");
            return response.json();
        })
        .then(data => {
            renderPage(data.content); // фильмы в поле content
            totalPages = data.totalPages;
            document.getElementById("pageInfo").textContent = `Страница ${page + 1} из ${totalPages}`;
            document.getElementById("prevBtn").classList.toggle("disabled", page === 0);
            document.getElementById("nextBtn").classList.toggle("disabled", page + 1 >= totalPages);
        })
        .catch(error => {
            document.getElementById("moviesContainer").innerHTML = "<p>Ошибка загрузки фильмов 😢</p>";
            console.error("Ошибка:", error);
        });
}

function renderPage(movies) {
    const container = document.getElementById("moviesContainer");
    container.innerHTML = "";

    if (!movies || movies.length === 0) {
        container.innerHTML = "<p>Нет фильмов на этой странице.</p>";
        return;
    }

    movies.forEach(movie => {
        const div = document.createElement("div");
        div.className = "movie-card";
        div.innerHTML = `
            <h2 class="movie-title">
                <a href="movie.html?id=${movie.id}">${movie.title}</a>
                <span class="movie-rating">⭐ ${movie.meanRating ?? '–'}</span>
            </h2>
            <div class="movie-year">Год: ${movie.year}</div>
            <div class="movie-description">${movie.description}</div>
            <div class="movie-views">👁️: ${movie.views ?? 0}</div>
        `;
        container.appendChild(div);
    });
}


function setupPagination() {
    document.getElementById("prevBtn").addEventListener("click", () => {
        if (currentPage > 0) {
            currentPage--;
            fetchAndRenderPage(currentPage);
        }
    });

    document.getElementById("nextBtn").addEventListener("click", () => {
        if (currentPage + 1 < totalPages) {
            currentPage++;
            fetchAndRenderPage(currentPage);
        }
    });
}

// Начальная инициализация
document.addEventListener("DOMContentLoaded", () => {
    setupPagination();
    fetchAndRenderPage(currentPage);

    fetch('/fragments/header.html')
        .then(response => response.text())
        .then(html => {
            document.getElementById('header-placeholder').innerHTML = html;
        })
        .catch(error => {
            console.error('Не удалось загрузить header:', error);
        });

    document.getElementById("searchBtn").addEventListener("click", () => {
        const query = document.getElementById("searchInput").value.trim();

        if (query === "") {
            currentPage = 0;
            fetchAndRenderPage(currentPage);
        } else {
            fetch(`/movie/search?title=${encodeURIComponent(query)}`)
                .then(response => {
                    if (!response.ok) throw new Error("Ошибка поиска");
                    return response.json();
                })
                .then(movies => {
                    renderPage(movies);
                    document.getElementById("pageInfo").textContent = `Найдено ${movies.length} результатов`;
                    document.getElementById("prevBtn").classList.add("disabled");
                    document.getElementById("nextBtn").classList.add("disabled");
                })
                .catch(err => {
                    document.getElementById("moviesContainer").innerHTML = "<p>Ошибка поиска 😢</p>";
                    console.error("Ошибка поиска:", err);
                });
        }
    });
});
