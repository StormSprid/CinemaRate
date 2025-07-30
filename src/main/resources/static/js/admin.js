    import { performMigration } from './migrate.js';


    // Загрузка header
    function loadHeader() {
        fetch('/fragments/adminHeader.html')
            .then(response => response.text())
            .then(html => {
                document.getElementById('header-placeholder').innerHTML = html;

                document.getElementById("migrateBtn")?.addEventListener("click", () => {
                    console.log("Нажата кнопка миграции");
                    performMigration(
                        () => alert("Миграция завершена!"),
                        () => alert("Ошибка миграции!")
                    );
                });

                // Инициализация логики ПОСЛЕ полной загрузки всех элементов
                setupSearch();
                setupStatusFilter();

                // Загружаем фильмы ТОЛЬКО после отрисовки фильтров и поиска
                const selectedStatus = document.querySelector('input[name="statusFilter"]:checked')?.value || "ALL";
                const searchQuery = document.getElementById("searchInput")?.value.trim() || "";
                loadMoviesWithFilter(selectedStatus, searchQuery);
            })
            .catch(error => {
                console.error('Не удалось загрузить header:', error);
            });
    }

    let currentPage = 0;
    const pageSize = 6;
    let totalPages = 1;

    function loadMoviesWithFilter(status = "ALL", title = "", page = 0) {
        let url = "";

        if (title && title.length > 0) {
            const params = new URLSearchParams();
            params.append("title", title);
            url = `/movie/search?${params.toString()}`;
        } else {
            const params = new URLSearchParams();
            if (status !== "ALL") params.append("status", status);
            params.append("page", page);
            params.append("size", pageSize);
            url = `/movie/filter?${params.toString()}`;
        }

        fetch(url)
            .then(response => response.json())
            .then(data => {
                const container = document.getElementById("moviesContainer");
                container.innerHTML = "";

                const movies = Array.isArray(data.content) ? data.content : (Array.isArray(data) ? data : []);

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

                // пагинация
                totalPages = data.totalPages;
                currentPage = data.number;

                updatePagination(status, title);
            })
            .catch(error => {
                console.error("Ошибка загрузки фильмов:", error);
                document.getElementById("moviesContainer").innerHTML = "<p>Ошибка загрузки данных 😢</p>";
            });
    }
    function updatePagination(status, title) {
        const pagination = document.getElementById("pagination");
        pagination.innerHTML = `
        <button id="prevPage" ${currentPage === 0 ? "disabled" : ""}>Предыдущая</button>
        <span>Страница ${currentPage + 1} из ${totalPages}</span>
        <button id="nextPage" ${currentPage + 1 >= totalPages ? "disabled" : ""}>Следующая</button>
    `;

        document.getElementById("prevPage").addEventListener("click", () => {
            if (currentPage > 0) {
                loadMoviesWithFilter(status, title, currentPage - 1);
            }
        });

        document.getElementById("nextPage").addEventListener("click", () => {
            if (currentPage + 1 < totalPages) {
                loadMoviesWithFilter(status, title, currentPage + 1);
            }
        });
    }


    function setupSearch() {
        const searchBtn = document.getElementById("searchBtn");
        const searchInput = document.getElementById("searchInput");

        searchBtn.addEventListener("click", () => {
            const query = searchInput.value.trim();
            const selectedStatus = document.querySelector('input[name="statusFilter"]:checked').value;
            loadMoviesWithFilter(selectedStatus, query);
        });
    }
    function setupStatusFilter() {
        const radios = document.querySelectorAll('input[name="statusFilter"]');
        radios.forEach(radio => {
            radio.addEventListener('change', () => {
                const statusRadio = document.querySelector('input[name="statusFilter"]:checked');
                const selectedStatus = statusRadio ? statusRadio.value : "ALL";
                const query = document.getElementById("searchInput").value.trim();
                loadMoviesWithFilter(selectedStatus, query);
            });
        });
    }


    // Инициализация при загрузке страницы
    document.addEventListener("DOMContentLoaded", () => {

        loadHeader();           // теперь внутри loadHeader вызовется setupSearch()
    });


