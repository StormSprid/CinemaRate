let currentPage = 1;
const pageSize = 6;
let allMovies = [];

function renderPage(page) {
    const start = (page - 1) * pageSize;
    const end = start + pageSize;
    const movies = allMovies.slice(start, end);

    const container = document.getElementById("moviesContainer");
    container.innerHTML = "";

    if (movies.length === 0) {
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
            <div class="movie-year">${movie.year}</div>
            <div class="movie-description">${movie.description}</div>
        `;
        container.appendChild(div);
    });

    document.getElementById("pageInfo").textContent = `Страница ${currentPage}`;
    document.getElementById("prevBtn").classList.toggle("disabled", currentPage === 1);
    document.getElementById("nextBtn").classList.toggle("disabled", end >= allMovies.length);
}


function setupPagination() {
    document.getElementById("prevBtn").addEventListener("click", () => {
        if (currentPage > 1) {
            currentPage--;
            renderPage(currentPage);
        }
    });

    document.getElementById("nextBtn").addEventListener("click", () => {
        if ((currentPage * pageSize) < allMovies.length) {
            currentPage++;
            renderPage(currentPage);
        }
    });
}

fetch("/movie/all")
    .then(response => response.json())
    .then(movies => {
        allMovies = movies;
        renderPage(currentPage);
        setupPagination();
    })
    .catch(error => {
        document.getElementById("moviesContainer").innerHTML = "<p>Ошибка загрузки фильмов 😢</p>";
        console.error("Ошибка:", error);
    });

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
        // Если пусто — показываем все
        fetch("/movie/all")
            .then(response => response.json())
            .then(movies => {
                allMovies = movies;
                currentPage = 1;
                renderPage(currentPage);
            });
    } else {
        fetch(`/movie/search?title=${encodeURIComponent(query)}`)
            .then(response => {
                if (!response.ok) throw new Error("Ошибка поиска");
                return response.json();
            })
            .then(movies => {
                allMovies = movies;
                currentPage = 1;
                renderPage(currentPage);
            })
            .catch(err => {
                document.getElementById("moviesContainer").innerHTML = "<p>Ошибка поиска 😢</p>";
                console.error("Ошибка поиска:", err);
            });
    }
});