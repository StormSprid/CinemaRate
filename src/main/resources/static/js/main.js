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
