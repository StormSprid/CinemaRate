async function fetchUserName() {
    const sessionId = sessionStorage.getItem("sessionId");


    if (!sessionId) {
        console.error("Session ID not found in localStorage");
        window.location.href = "/login.html"; // редирект на страницу входа
        return;
    }

    try {
        const response = await fetch("user/getName", {
            method: "GET",
            headers: {
                "X-Session-Id": sessionId
            }
        });

        if (!response.ok) {
            throw new Error("Failed to fetch user name");
        }

        const userName = await response.text(); // plain text response
        document.getElementById("username-display").innerText = "Hello, " + userName + "!";
    } catch (error) {
        console.error("Error fetching user name:", error);
        document.getElementById("username-display").innerText = "Error loading name";
    }
}

// Запуск после загрузки страницы
fetchUserName();