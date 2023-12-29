const express = require("express");
const app = express();
const bodyParser = require("body-parser");
const axios = require("axios");
const mysql = require("mysql2");

const pool = mysql.createPool({
  host: "34.64.248.240",
  user: "root",
  password: "7007",
  database: "fortress",
  waitForConnections: true,
  connectionLimit: 10,
  queueLimit: 0,
});

// 공공데이터를 받아옴
async function getAddress(url) {
  try {
    const response = await axios.get(url);
    return response.data;
  } catch (error) {
    console.error(`Error: ${error}`);
    return null;
  }
}

async function parseHouseData(data) {
  const houseData = data.response.body.item;
  const parsedData = Array.isArray(houseData)
    ? houseData.map((item) => {
        return {
          법정동: item.법정동,
          지번: item.지번,
          연립다세대: item.연립다세대,
          층: item.층,
        };
      })
    : []; //check if not array

  //json 형식 string으로 변환
  const jsonString = JSON.stringify(parsedData);

  return jsonString;
}

const apiKey =
  "vL7DskXBk%2Bms849FYBP2JmNGj8IVVxfhyXJrFLKS9HX8JWbalVpBj7Kc6i5PE6qTXo8MUgCI9j4BHP4LIoIKCQ%3D%3D";
const apiUrl = `http://openapi.molit.go.kr:8081/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcRHRent?_wadl&type=xml&serviceKey=${apiKey}`;

app.get("/addresses", function (req, res) {
  getAddress(apiUrl)
    .then((data) => {
      const parsedData = parseHouseData(data);
      res.send(parsedData);
    })
    .catch((error) => {
      console.error(`Error: ${error}`);
      res.status(500).send("Internal Server Error");
    });
});

app.post("/report/address", function (req, res) {
  getAddress(apiUrl)
    .then((data) => {
      const parsedData = parseHouseData(data);
      res.send(parsedData);
    })
    .catch((error) => {
      console.error(`Error: ${error}`);
      res.status(500).send("Internal Server Error");
    });
});

// Middleware to parse JSON in the request body
app.use(bodyParser.json());

// 보증금 유형 선택
app.post("/report/housetype", (req, res) => {
  try {
    const { price_type } = req.body;

    res
      .status(200)
      .json({ message: "Successfully processed price_type", data: price_type });
  } catch (error) {
    console.error(`Error: ${error}`);
    res.status(500).json({ error: "Internal Server Error" });
  }
});

// 보증금, 월세 입력
app.post("/report/price", (req, res) => {
  try {
    const { user_deposit, user_month } = req.body;

    res.status(200).json({
      message: "Successfully processed user payment",
      data: { user_deposit, user_month },
    });
  } catch (error) {
    console.error(`Error: ${error}`);
    res.status(500).json({ error: "Internal Server Error" });
  }
});

// 집 주소 목록 불러오기
app.get("/addresses", (req, res) => {
  try {
    // Your SQL query to fetch addresses from the database
    const sql = "SELECT address_id, address FROM address";

    pool.query(sql, (error, results) => {
      if (error) {
        console.error(`Database Error: ${error}`);
        res.status(500).json({ error: "Internal Server Error" });
      } else {
        const addresses = results.map((row) => ({
          address_id: row.address_id,
          address: row.address,
        }));
        res.status(200).json({ addresses });
      }
    });
  } catch (error) {
    console.error(`Error: ${error}`);
    res.status(500).json({ error: "Internal Server Error" });
  }
});

//집 주소 입력
app.post("/report/address", (req, res) => {
  try {
    const { address_id } = req.body;

    // request body에 address_id 존재하는지 확인
    if (!address_id) {
      return res
        .status(400)
        .json({ error: "Missing address_id in the request body" });
    }

    const sql = "SELECT address_id, address FROM address WHERE address_id = ?";

    pool.query(sql, [address_id], (error, results) => {
      if (error) {
        console.error(`Database Error: ${error}`);
        res.status(500).json({ error: "Internal Server Error" });
      } else if (results.length === 0) {
        res.status(404).json({ error: "Address not found" });
      } else {
        const address = {
          address_id: results[0].address_id,
          address: results[0].address,
        };
        res.status(200).json({ address });
      }
    });
  } catch (error) {
    console.error(`Error: ${error}`);
    res.status(500).json({ error: "Internal Server Error" });
  }
});

app.listen(3000, () => console.log("3000번 포트 대기"));
