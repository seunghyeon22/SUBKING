
const kcalInput = document.querySelector(".kcal_input");
const kcalBtn = document.querySelector(".kcal_button");


kcalBtn.addEventListener("click", function() {

	let kcal = kcalInput.value;
	let url = "/240930subKingProject/api/v1/kcal";
	fetch(url, {
		method: "POST",
		headers: {
			"Content-Type": "application/json",
		},
		body: JSON.stringify(kcal),
	})
		.then((resp) => resp.json())
		.then((dataArr)=> console.log(dataArr));
})