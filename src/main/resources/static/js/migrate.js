// migrate.js

export function performMigration(onSuccess, onError) {
    const loader = document.getElementById("loader");
    loader.style.display = "block";

    fetch("/movie/migration", { method: "POST" })
        .then(response => {
            if (!response.ok) throw new Error("Ошибка миграции");
            return response.text();
        })
        .then(result => {
            loader.style.display = "none";
            if (onSuccess) onSuccess(result);
        })
        .catch(err => {
            loader.style.display = "none";
            if (onError) onError(err);
        });
}
