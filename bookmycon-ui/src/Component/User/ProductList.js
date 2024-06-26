import React, { useState, useEffect } from 'react'
import axios from 'axios'
import { Button } from '@mui/material';
import Card from "@mui/material/Card";
import CardActions from "@mui/material/CardActions";
import CardContent from "@mui/material/CardContent";
import CardMedia from "@mui/material/CardMedia";
import Typography from "@mui/material/Typography";
import CartIcon from "@mui/icons-material/ShoppingCart";
import { useDispatch, useSelector } from 'react-redux';


const ProductList = () => {
    const [allProducts, setAllProducts] = useState([]);

    const dispatch = useDispatch()
    const cart = useSelector((state) => state)
    useEffect(() => {
        fetchRProducts();
    }, []);

    const fetchRProducts = () => {
        axios
            .get("http://localhost:8080/product/getAllProduct")
            .then((response) => {
                setAllProducts(response.data);
            });
    };

    const addToCart = (product) => {
        if (sessionStorage.getItem("userLogin")) {
            dispatch({ type: 'ADD', payload: product })
        } else {
            alert("login please..")
        }
    }

    return (
        <div className="d-flex container">
            <div className="flex-wrap">
                {allProducts.map(product => {
                    product.quantity=1
                    return (
                        <Card className="m-4 text-center" sx={{ display: "inline-flex", flexDirection: "column", height: "500" }}>
                           <CardMedia component="img" alt="" height="200" sx={{ width: 275 }} image={"https://images.unsplash.com/photo-1577968897966-3d4325b36b61?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1374&q=80"} />
                            <CardContent sx={{ flexGrow: 1 }}>
                                <Typography sx={{ fontSize: 30 }} variant="body1">{product.productName}</Typography>
                                <Typography color={"#F5A74A"} gutterBottom variant="h5" component="div">
                                    ₹ {product.productPrice}
                                </Typography>
                            </CardContent>
                            <CardActions sx={{ mx: 5 }}>
                                <Button size="small" onClick={() => addToCart(product)}>
                                    <CartIcon sx={{ mx: 2 }} />
                                    Add To Cart
                                </Button>
                            </CardActions>
                        </Card>
                    )
                })}
            </div>

        </div>

    );
};

export default ProductList;
