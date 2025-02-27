document.addEventListener("DOMContentLoaded", function () {
    fetch('/ShoppingCart/products')
        .then(response => response.json())
        .then(data => {
            let productsDiv = document.getElementById("products");
            data.forEach(product => {
                let item = document.createElement("div");
                item.innerHTML = `<h3>${product.name} - $${product.price}</h3>
                                  <button onclick="addToCart('${product.name}', ${product.price})">Add to Cart</button>`;
                productsDiv.appendChild(item);
            });
        });
});

function addToCart(name, price) {
    let cart = JSON.parse(localStorage.getItem("cart")) || [];
    cart.push({ name, price });
    localStorage.setItem("cart", JSON.stringify(cart));
    alert(`${name} added to cart!`);
}
