/**
 * 
 * 
 * 
 */
loadData();

let breads = [];
let vegetables = [];
let pattys = [];
let sauces = [];
let cheezes = [];
let sides = [];
let drinks = [];


function loadCustomData() {
	let url = "/240930subKingProject/api/v1/custom";
	return fetch(url)
		.then((resp) => resp.json());

}
function loadData() {
	loadCustomData().then((igArr) => {
		for (let i = 0; i < igArr.length; i++) {
			if (igArr[i].ig_category == "빵") {
				breads.push(igArr[i]);

			}
			if (igArr[i].ig_category == "야채") {
				vegetables.push(igArr[i]);
			}
			if (igArr[i].ig_category == "패티") {
				pattys.push(igArr[i]);
			}
			if (igArr[i].ig_category == "소스") {
				sauces.push(igArr[i]);
			}
			if (igArr[i].ig_category == "치즈") {
				cheezes.push(igArr[i]);
			}
		}
		selectBread();
	})
}
function selectBread() {
	const list = document.querySelector(".list");
	let tableStr = ``;
	for (let i = 0; i < breads.length; i++) {
		tableStr += `	
		<li class="breads">
			<div class="img">
				<img alt="재료이름" src="data:image/jpeg;base64,${breads[i].ig_image}">
			</div>
			<input type="hidden" class="ig_id" value = "${breads[i].ig_no}">
			<strong class="tit">${breads[i].ig_name}</strong> 
			<span class="eng">${breads[i].ig_price}원</span>
			<span class="cal">${breads[i].ig_kcal}kcal</span>
	 
		</li>
		`;
	}
	for (let i = 0; i < vegetables.length; i++) {
		tableStr += `	
			<li class="vegetables">
				<div class="img">
					<img alt="재료이름" src="data:image/jpeg;base64,${vegetables[i].ig_image}">
				</div>
				<input type="hidden" class="ig_id" value = "${vegetables[i].ig_no}">
				<strong class="tit">${vegetables[i].ig_name}</strong> 
				<span class="eng">${vegetables[i].ig_price}원</span>
				<span class="cal">${vegetables[i].ig_kcal}kcal</span>
		 
			</li>
			`;
	}
	for (let i = 0; i < pattys.length; i++) {
		tableStr += `	
				<li class="pattys">
					<div class="img">
						<img alt="재료이름" src="data:image/jpeg;base64,${pattys[i].ig_image}">
					</div>
					<input type="hidden" class="ig_id" value = "${pattys[i].ig_no}">
					<strong class="tit">${pattys[i].ig_name}</strong> 
					<span class="eng">${pattys[i].ig_price}원</span>
					<span class="cal">${pattys[i].ig_kcal}kcal</span>
			 
				</li>
				`;
	}
	for (let i = 0; i < sauces.length; i++) {
		tableStr += `	
					<li class="breads">
						<div class="img">
							<img alt="재료이름" src="data:image/jpeg;base64,${sauces[i].ig_image}">
						</div>
						<input type="hidden" class="ig_id" value = "${sauces[i].ig_no}">
						<strong class="tit">${sauces[i].ig_name}</strong> 
						<span class="eng">${sauces[i].ig_price}원</span>
						<span class="cal">${sauces[i].ig_kcal}kcal</span>
				 
					</li>
					`;
	}
	for (let i = 0; i < cheezes.length; i++) {
		tableStr += `	
									<li class="breads">
										<div class="img">
											<img alt="재료이름" src="data:image/jpeg;base64,${cheezes[i].ig_image}">
										</div>
										<input type="hidden" class="ig_id" value = "${cheezes[i].ig_no}">
										<strong class="tit">${cheezes[i].ig_name}</strong> 
										<span class="eng">${cheezes[i].ig_price}원</span>
										<span class="cal">${cheezes[i].ig_kcal}kcal</span>
								 
									</li>
									`;
	}

	list.innerHTML = tableStr;
}