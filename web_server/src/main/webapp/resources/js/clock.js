function updateClock() {
    const clockElement = document.getElementById('clock');

    if (!clockElement) {
        console.error('Element with id "clock" not found.');
        return;
    }

    const now = new Date();
    const formattedTime = now.toLocaleTimeString('ru-RU', { hour: '2-digit', minute: '2-digit', second: '2-digit' });
    const formattedDate = now.toLocaleDateString('ru-RU', { day: '2-digit', month: 'long', year: 'numeric' });

    clockElement.innerHTML = `
        <div class="date">${formattedDate}</div>
        <div class="time">${formattedTime}</div>
    `;
}

updateClock();

setInterval(updateClock, 11000);
