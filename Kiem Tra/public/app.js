// Lấy dữ liệu từ server
async function loadProducts() {
    try {
        const response = await fetch('http://localhost:3000/api/products');
        const products = await response.json();

        const tableBody = document.querySelector('#productsTable tbody');
        tableBody.innerHTML = '';

        products.forEach(product => {
            const row = document.createElement('tr');
            row.innerHTML = `
                <td>${product.id}</td>
                <td>${product.name}</td>
                <td>${product.price}</td>
                <td>
                    <button onclick="deleteProduct(${product.id})">Xóa</button>
                </td>
            `;
            tableBody.appendChild(row);
        });
    } catch (error) {
        console.error('Lỗi khi tải sản phẩm:', error);
    }
}

// Thêm sản phẩm mới
async function addProduct() {
    const name = document.getElementById('productName').value;
    const price = document.getElementById('productPrice').value;

    if (!name || !price) {
        alert('Vui lòng nhập đầy đủ thông tin');
        return;
    }

    try {
        const response = await fetch('http://localhost:3000/api/products', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ name, price: parseFloat(price) }),
        });

        if (response.ok) {
            document.getElementById('productName').value = '';
            document.getElementById('productPrice').value = '';
            loadProducts();
        }
    } catch (error) {
        console.error('Lỗi khi thêm sản phẩm:', error);
    }
}

// Xóa sản phẩm
async function deleteProduct(id) {
    if (!confirm('Bạn có chắc muốn xóa sản phẩm này?')) return;

    try {
        const response = await fetch(`http://localhost:3000/api/products/${id}`, {
            method: 'DELETE',
        });

        if (response.ok) {
            loadProducts();
        }
    } catch (error) {
        console.error('Lỗi khi xóa sản phẩm:', error);
    }
}

// Tải dữ liệu khi trang được load
document.addEventListener('DOMContentLoaded', loadProducts);