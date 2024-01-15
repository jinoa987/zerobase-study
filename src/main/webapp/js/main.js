/**
 * 현재 나의 위치 가져오기
 */
function getMyPosition() {
    navigator.geolocation.getCurrentPosition((position) => {

        const lat = document.getElementById('lat');
        const lnt = document.getElementById('lnt');

        lat.value = position.coords.latitude;
        lnt.value = position.coords.longitude;

        location.href = "?lat=" + lat.value + "&lnt=" + lnt.value;
    });
}

/**
 * 경도, 위도로 거리 계산하기
 */
function distance(lat1, lnt1, lat2, lnt2) {
    const R = 6371; // 지구 반지름 (단위: km)
    const dLat = deg2rad(lat2 - lat1);
    const dLnt = deg2rad(lnt2 - lnt1);
    const a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
        Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) *
        Math.sin(dLnt / 2) * Math.sin(dLnt / 2);
    const c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    const dist = R * c; // 두 지점 간의 거리 (단위: km)
    return dist;
}

function deg2rad(deg) {
    return deg * (Math.PI / 180);
}

/**
 * 테이블에 동적으로 행 추가
 */
function addRow(dto) {
    const dynamicTable = document.getElementById('info-table');
    const newRow = dynamicTable.insertRow();

    for (let i = 0; i < 17; i++) {
        const cell = newRow.insertCell();
        cell.innerText = i.toString();
    }

}

