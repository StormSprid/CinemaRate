// js/sessionUtils.js

export async function fetchUserName() {
    console.log("fetchUserName called");
    const sessionId = localStorage.getItem("sessionId");

    if (!sessionId) {
        console.error("Session ID not found in localStorage");
        return null;
    }

    try {
        const response = await fetch("/getName", {
            method: "GET",
            headers: {
                "X-Session-Id": sessionId
            }
        });

        if (!response.ok) {
            throw new Error("Failed to fetch user name");
        }

        const userName = await response.text();
        return userName;  // <<<<< ВОТ ТУТ возвращаем имя

    } catch (error) {
        console.error("Error fetching user name:", error);
        return null;
    }
}
