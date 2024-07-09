var form = document.getElementById("myForm"),
    imgInput = document.querySelector(".img"),
    file = document.getElementById("imgInput"),
    userName = document.getElementById("name"),
    email = document.getElementById("email"),
    phone = document.getElementById("phone"),
    city = document.getElementById("city"),
    size = document.getElementById("size"),
    quantity = document.getElementById("quantity"),
    outfit = document.getElementById("outfit"),
    sDate = document.getElementById("sDate"),
    submitBtn = document.querySelector(".submit"),
    userInfo = document.getElementById("data"),
    modal = document.getElementById("userForm"),
    modalTitle = document.querySelector("#userForm .modal-title"),
    newUserBtn = document.querySelector(".newUser")


let getData = localStorage.getItem('userProfile') ? JSON.parse(localStorage.getItem('userProfile')) : []

let isEdit = false, editId
showInfo()

newUserBtn.addEventListener('click', ()=> {
    submitBtn.innerText = 'Submit',
    modalTitle.innerText = "Fill the Form"
    isEdit = false
    imgInput.src = "./images/Profile Icon.webp"
    form.reset()
})


file.onchange = function(){
    if(file.files[0].size < 1000000){  // 1MB = 1000000
        var fileReader = new FileReader();

        fileReader.onload = function(e){
            imgUrl = e.target.result
            imgInput.src = imgUrl
        }

        fileReader.readAsDataURL(file.files[0])
    }
    else{
        alert("This file is too large!")
    }
}


function showInfo(){
    document.querySelectorAll('.employeeDetails').forEach(info => info.remove())
    getData.forEach((element, index) => {
        let createElement = `<tr class="employeeDetails">
            <td>${index+1}</td>
            <td><img src="${element.picture}" alt="" width="50" height="50"></td>
            <td>${element.employeeName}</td>

            <td>${element.employeeEmail}</td>
            <td>${element.employeePhone}</td>
            <td>${element.employeeCity}</td>
            <td>${element.employeesize}</td>
            <td>${element.employeequantity}</td>
            <td>${element.employeeoutfit}</td>
            <td>${element.startDate}</td>


            <td>
                <button class="btn btn-success" onclick="readInfo('${element.picture}', '${element.employeeName}', '${element.employeeEmail}', '${element.employeePhone}', '${element.employeeCity}', '${element.employeequantity}','${element.employeeoutfit}', '${element.startDate}')" data-bs-toggle="modal" data-bs-target="#readData"><i class="bi bi-eye"></i></button>

                <button class="btn btn-primary" onclick="editInfo(${index}, '${element.picture}', '${element.employeeName}', '${element.employeeEmail}', '${element.employeePhone}', '${element.employeeCity}', '${element.employeequantity}', '${element.employeeoutfit}', '${element.startDate}')" data-bs-toggle="modal" data-bs-target="#userForm"><i class="bi bi-pencil-square"></i></button>

                <button class="btn btn-danger" onclick="deleteInfo(${index})"><i class="bi bi-trash"></i></button>
                            
            </td>
        </tr>`

        userInfo.innerHTML += createElement
    })
}
showInfo()


function readInfo(pic, name, email, phone, city, size, quantity, outfit, sDate){
    document.querySelector('.showImg').src = pic,
    document.querySelector('#showName').value = name,
    document.querySelector("#showEmail").value = email,
    document.querySelector("#showPhone").value = phone,
    document.querySelector("#showcity").value = city,

    document.querySelector("#showsize").value = size,
    document.querySelector("#showquantity").value = quantity,
    document.querySelector("#showoutfit").value = outfit,

    document.querySelector("#showsDate").value = sDate
}


function editInfo(index, pic, name, Email, Phone, city, size, quantity, outfit, Sdate){
    isEdit = true
    editId = index
    imgInput.src = pic
    userName.value = name
    Email.value = Email
    Phone.value =Phone
    city.value = city,
    size.value = size,
    quantity.value = quantity,
    outfit.value = outfit,
    sDate.value = Sdate

    submitBtn.innerText = "Update"
    modalTitle.innerText = "Update The Form"
}
function success(){
    alert("Thank You for Your ordering !");
    window.location.replace("tq.html");

}

function deleteInfo(index){
    if(confirm("Are you sure want to delete?")){
        getData.splice(index, 1)
        localStorage.setItem("userProfile", JSON.stringify(getData))
        showInfo()
    }
}


form.addEventListener('submit', (e)=> {
    e.preventDefault()

    const information = {
        picture: imgInput.src == undefined ? "./image/Profile Icon.webp" : imgInput.src,
        employeeName: userName.value,
        employeeEmail: email.value,
        employeePhone: phone.value,
        employeeCity: city.value,
        employeesize: size.value,

        employeequantity: quantity.value,


        employeeoutfit: outfit.value,
        startDate: sDate.value
    }

    if(!isEdit){
        getData.push(information)
    }
    else{
        isEdit = false
        getData[editId] = information
    }

    localStorage.setItem('userProfile', JSON.stringify(getData))

    submitBtn.innerText = "Submit"
    modalTitle.innerHTML = "Fill The Form"

    showInfo()

    form.reset()

    imgInput.src = "images/ProfileIcon.webp"  

    // modal.style.display = "none"
    // document.querySelector(".modal-backdrop").remove()

})
document.addEventListener('DOMContentLoaded', () => {
    const cartItemsContainer = document.querySelector('.cart_items');
    const totalPriceContainer = document.querySelector('.total_price');
    const addToCartButtons = document.querySelectorAll('.btn');

    const cart = [];

    addToCartButtons.forEach(button => {
        button.addEventListener('click', () => {
            const productTitle = button.closest('.box_main').querySelector('.shirt_text').innerText;
            const productPrice = parseFloat(button.closest('.box_main').querySelector('.price_text span').innerText.replace('$', ''));

            const product = {
                title: productTitle,
                price: productPrice
            };

            addToCart(product);
            updateCartUI();
        });
    });

    function addToCart(product) {
        const existingProduct = cart.find(item => item.title === product.title);

        if (existingProduct) {
            existingProduct.quantity += 1;
        } else {
            product.quantity = 1;
            cart.push(product);
        }
    }

    function updateCartUI() {
        cartItemsContainer.innerHTML = '';
        let total = 0;

        cart.forEach(product => {
            const cartItem = document.createElement('li');
            cartItem.classList.add('cart_item');
            cartItem.innerHTML = `${product.title} - $${product.price} x ${product.quantity}`;
            cartItemsContainer.appendChild(cartItem);

            total += product.price * product.quantity;
        });

        totalPriceContainer.innerText = `Total: $${total.toFixed(2)}`;
    }
});
document.addEventListener('DOMContentLoaded', () => {
    const cartItemsContainer = document.querySelector('.cart_items');
    const totalPriceContainer = document.querySelector('.total_price');
    const addToCartButtons = document.querySelectorAll('.add-to-cart');

    const cart = [];

    addToCartButtons.forEach(button => {
        button.addEventListener('click', () => {
            const productTitle = button.getAttribute('data-title');
            const productPrice = parseFloat(button.getAttribute('data-price'));

            const product = {
                title: productTitle,
                price: productPrice
            };

            addToCart(product);
            updateCartUI();
        });
    });

    function addToCart(product) {
        const existingProduct = cart.find(item => item.title === product.title);

        if (existingProduct) {
            existingProduct.quantity += 1;
        } else {
            product.quantity = 1;
            cart.push(product);
        }
    }

    function updateCartUI() {
        cartItemsContainer.innerHTML = '';
        let total = 0;

        cart.forEach(product => {
            const cartRow = document.createElement('tr');
            const subtotal = product.price * product.quantity;
            cartRow.innerHTML = `
                <td>${product.title}</td>
                <td>$${product.price.toFixed(2)}</td>
                <td>${product.quantity}</td>
                <td>$${subtotal.toFixed(2)}</td>
            `;
            cartItemsContainer.appendChild(cartRow);

            total += subtotal;
        });

        totalPriceContainer.innerText = `Total: $${total.toFixed(2)}`;
    }
});
