let currentDate = new Date();
let currentMonth = currentDate.getMonth() + 1;
let currentYear = currentDate.getFullYear();

document.addEventListener('DOMContentLoaded', () => {
    console.log('DOMContentLoaded 이벤트 발생');
    loadSalesData();
});

function loadSalesData() {
    console.log('loadSalesData 호출됨');
    let url = "/240930subKingProject/api/v1/sales";
    fetch(url)
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            return response.json();
        })
        .then(data => {
            const calendar = document.getElementById('calendar');
            const monthSales = document.getElementById('month-sales');
            const monthYear = document.getElementById('month-year');
            calendar.innerHTML = '';
            monthSales.innerHTML = '';
            monthYear.innerHTML = `${currentYear}년 ${currentMonth}월`;

            let monthlyTotal = 0;
            const firstDayOfMonth = new Date(currentYear, currentMonth - 1, 1).getDay();
            const daysInMonth = new Date(currentYear, currentMonth, 0).getDate();

            for (let i = 0; i < firstDayOfMonth; i++) {
                const emptyDayElement = document.createElement('div');
                emptyDayElement.classList.add('empty-day');
                calendar.appendChild(emptyDayElement);
            }

            for (let day = 1; day <= daysInMonth; day++) {
                const dateStr = `${currentYear}-${String(currentMonth).padStart(2, '0')}-${String(day).padStart(2, '0')}`;
                const sale = data.find(item => {
                    const saleDate = new Date(item.sale_date);
                    const localDateStr = new Date(saleDate.getTime() - (saleDate.getTimezoneOffset() * 60000)).toISOString().split('T')[0];
                    return localDateStr === dateStr;
                });

                const dayElement = document.createElement('div');
                dayElement.classList.add('day');

                const dayOfWeek = new Date(currentYear, currentMonth - 1, day).getDay();
                if (dayOfWeek === 0) dayElement.classList.add('sunday');
                if (dayOfWeek === 6) dayElement.classList.add('saturday');

                dayElement.innerHTML = `
                    <div>${day}일</div>
                    <div>&nbsp;</div>
                    <div class="total-sales">${sale ? sale.total_sales + '원' : '매출 없음'}</div>
                `;

                const totalSalesElement = dayElement.querySelector('.total-sales');
                totalSalesElement.style.color = 'black';

                calendar.appendChild(dayElement);

                if (sale) {
                    monthlyTotal += sale.total_sales;
                }
            }

            monthSales.innerHTML = `${currentYear}년 ${currentMonth}월 매출: ${monthlyTotal}원`;
        })
        .catch(error => {
            console.error('Error loading sales data:', error.message);
            console.error(error.stack);
        });
}

function previousMonth() {
    if (currentMonth === 1) {
        currentMonth = 12;
        currentYear--;
    } else {
        currentMonth--;
    }
    loadSalesData();
}

function nextMonth() {
    if (currentMonth === 12) {
        currentMonth = 1;
        currentYear++;
    } else {
        currentMonth++;
    }
    loadSalesData();
}

function previousYear() {
    currentYear--;
    loadSalesData();
}

function nextYear() {
    currentYear++;
    loadSalesData();
}
