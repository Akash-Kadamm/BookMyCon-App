import React from "react";
import { useDispatch, useSelector } from 'react-redux';
import { useNavigate } from 'react-router-dom';


const Cart = () => {
    const cart = useSelector((state) => state)
    const navigate = useNavigate()
    const dispatch = useDispatch();
    const cartTotal=0;
    const emptyCart=<img className="w-75" src="https://www.vinsolutions.com/wp-content/uploads/sites/2/vinsolutions/media/Vin-Images/news-blog/Empty_Shopping_Cart_blog.jpg" alt="" />
    
    const placeOrder = () => {

    }

    const removeFoodItem = (product) => {

    }


    return (
        <div className="my-5 container">
            <div className="row">{cart.length === 0 && emptyCart}</div>
            <div className="container d-flex text-center">

                {cart.map((product) => {
                    return (
                        <div
                            key={product.productId}
                            className="card m-3"
                            style={{ width: "22rem" }}
                        >
                            <button
                                onClick={() => dispatch({ type: "REMOVE", payload: product })}
                                className="btn btn-danger"
                            >
                                Remove
                            </button>
                            <div className="m-2">
                                <img
                                    src={"http://localhost:8080/restaurant/" + product.thumbnail}
                                    className="card-img-top rounded border border-primary"
                                    alt={product.productName}
                                    style={{ height: "15rem" }}
                                />
                            </div>
                            <div className="card-body">
                                <h5 className="card-title">{product.productName}</h5>
                            </div>
                            <div>
                                <button
                                    onClick={() => dispatch({ type: "INCREASE", payload: product })}
                                    className="btn btn-warning"
                                >
                                    +
                                </button>
                                <span className="mx-4">{product.productAvailableQTY}</span>
                                <button
                                    onClick={() => {
                                        removeFoodItem(product);
                                    }}
                                    className="btn btn-warning"
                                >
                                    -
                                </button>
                            </div>
                            <div className="h3 my-2">
                                Total Price: ₹ {product.productPrice * product.productAvailableQTY}
                            </div>
                        </div>
                    );
                })}
            </div>
            <div className="mx-5 row">
                <span className="h3 col text-black text fw-bold">
                    Total amount: ₹ {cartTotal} /-
                </span>
                <div className="col">
                    <button data-bs-toggle="modal" data-bs-target="#exampleModal" onClick={placeOrder} className="btn btn-info float-end">
                        Place Order
                    </button>
                </div>
            </div>
        </div>
    );
}

export default Cart;