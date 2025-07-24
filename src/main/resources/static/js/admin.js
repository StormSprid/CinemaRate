// Загрузка всех фильмов при открытии страницы
function loadAllMovies() {
    fetch("/movie/all")
        .then(response => response.json())
        .then(movies => {
            const container = document.getElementById("moviesContainer");
            container.innerHTML = "";

            if (movies.length === 0) {
                container.innerHTML = "<p>Нет фильмов в базе.</p>";
                return;
            }

            movies.forEach(movie => {
                const div = document.createElement("div");
                div.className = "movie-card";
                div.innerHTML = `
                    <h2 class="movie-title">
                        <a href="adminMovie.html?id=${movie.id}">${movie.title}</a>
                    </h2>
                    <div class="movie-year">${movie.year}</div>
                    <div class="movie-description">${movie.description}</div>
                `;
                container.appendChild(div);
            });
        })
        .catch(error => {
            const container = document.getElementById("moviesContainer");
            container.innerHTML = "<p>Ошибка загрузки данных 😢</p>";
            console.error("Ошибка:", error);
        });
}

// Загрузка header
function loadHeader() {
    fetch('/fragments/adminHeader.html')
        .then(response => response.text())
        .then(html => {
            document.getElementById('header-placeholder').innerHTML = html;
        })
        .catch(error => {
            console.error('Не удалось загрузить header:', error);
        });
}

// Поиск фильмов
function setupSearch() {
    const searchBtn = document.getElementById("searchBtn");
    const searchInput = document.getElementById("searchInput");

    searchBtn.addEventListener("click", () => {
        const query = searchInput.value.trim();

        if (query === "") {
            loadAllMovies(); // Показать все фильмы
        } else {
            fetch(`/movie/search?title=${encodeURIComponent(query)}`)
                .then(res => {
                    if (!res.ok) throw new Error("Ошибка поиска");
                    return res.json();
                })
                .then(movies => {
                    const container = document.getElementById("moviesContainer");
                    container.innerHTML = "";

                    if (movies.length === 0) {
                        container.innerHTML = "<p>Фильмы не найдены 😢</p>";
                        return;
                    }

                    movies.forEach(movie => {
                        const div = document.createElement("div");
                        div.className = "movie-card";
                        div.innerHTML = `
                            <h2 class="movie-title">
                                <a href="adminMovie.html?id=${movie.id}">${movie.title}</a>
                            </h2>
                            <div class="movie-year">${movie.year}</div>
                            <div class="movie-description">${movie.description}</div>
                        `;
                        container.appendChild(div);
                    });
                })
                .catch(err => {
                    console.error("Ошибка поиска:", err);
                    document.getElementById("moviesContainer").innerHTML = "<p>Фильмы не найдены 😢</p>";
                });
        }
    });
}

// Инициализация при загрузке страницы
document.addEventListener("DOMContentLoaded", () => {
    loadAllMovies();
    loadHeader();
    setupSearch();
});
