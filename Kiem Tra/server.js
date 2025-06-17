require('dotenv').config();
const express = require('express');
const sql = require('mssql');
const cors = require('cors');
const app = express();
const port = 3000;

// Cấu hình kết nối SQL Server từ file .env
const config = {
    user: process.env.DB_USER,
    password: process.env.DB_PASSWORD,
    server: process.env.DB_SERVER,
    database: process.env.DB_NAME,
    options: {
        encrypt: true,
        trustServerCertificate: true
    }
};

// Middleware
app.use(cors());
app.use(express.json());
app.use(express.static('public'));

// Kết nối đến SQL Server
sql.connect(config).then(pool => {
    console.log('Kết nối SQL Server thành công');

    // Tạo bảng Products nếu chưa tồn tại
    pool.request().query(`
        IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='Products' AND xtype='U')
        CREATE TABLE Products (
            id INT PRIMARY KEY IDENTITY(1,1),
            name NVARCHAR(100) NOT NULL,
            price DECIMAL(10, 2) NOT NULL
        )
    `);

    // API lấy tất cả sản phẩm
    app.get('/api/products', async (req, res) => {
        try {
            const result = await pool.request().query('SELECT * FROM Products');
            res.json(result.recordset);
        } catch (err) {
            res.status(500).json({ error: err.message });
        }
    });

    // API thêm sản phẩm
    app.post('/api/products', async (req, res) => {
        const { name, price } = req.body;
        try {
            await pool.request()
                .input('name', sql.NVarChar, name)
                .input('price', sql.Decimal, price)
                .query('INSERT INTO Products (name, price) VALUES (@name, @price)');
            res.status(201).json({ message: 'Thêm sản phẩm thành công' });
        } catch (err) {
            res.status(500).json({ error: err.message });
        }
    });

    // API xóa sản phẩm
    app.delete('/api/products/:id', async (req, res) => {
        try {
            await pool.request()
                .input('id', sql.Int, req.params.id)
                .query('DELETE FROM Products WHERE id = @id');
            res.json({ message: 'Xóa sản phẩm thành công' });
        } catch (err) {
            res.status(500).json({ error: err.message });
        }
    });

    // Khởi động server
    app.listen(port, () => {
        console.log(`Server đang chạy tại http://localhost:${port}`);
    });
}).catch(err => {
    console.error('Lỗi kết nối SQL Server:', err);
});