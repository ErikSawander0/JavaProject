console.log("test från js");



const dayNames = ["Sö","Må","Ti","On","To","Fr","Lö"];

let current = new Date();

function renderCalendar() {

    let y = current.getFullYear();
    let m = current.getMonth();

    document.getElementById("monthTitle").textContent =
        current.toLocaleDateString("sv-SE", { month: "long", year: "numeric" });

    // Render day names
    let dn = document.getElementById("dayNames");
    dn.innerHTML = "";
    dayNames.forEach(d => {
        let div = document.createElement("div");
        div.textContent = d;
        dn.appendChild(div);
    });

    // Render days
    let cd = document.getElementById("calendarDays");
    cd.innerHTML = "";

    let firstDay = new Date(y, m, 1).getDay();
    let daysInMonth = new Date(y, m + 1, 0).getDate();

    for (let i = 0; i < firstDay; i++)
        cd.appendChild(document.createElement("div"));

    for (let d = 1; d <= daysInMonth; d++) {

        let btn = document.createElement("button");
        btn.className = "day-btn";
        btn.textContent = d.toString().padStart(2, "0");

        btn.onclick = () => {
            let selected =
                `${y}-${String(m+1).padStart(2,'0')}-${String(d).padStart(2,'0')}`;

            // skriv in i hidden input
            document.getElementById("dateForm:selectedDate").value = selected;

            // trigga JSF AJAX
            document.getElementById("dateForm:ajaxUpdate").click();

            cd.querySelectorAll(".day-btn").forEach(b => b.classList.remove("active"));
            btn.classList.add("active");
        };

        cd.appendChild(btn);
    }
}

document.getElementById("prevMonth").onclick = () => {
    current.setMonth(current.getMonth() - 1);
    renderCalendar();
};

document.getElementById("nextMonth").onclick = () => {
    current.setMonth(current.getMonth() + 1);
    renderCalendar();
};

window.onload = renderCalendar;
