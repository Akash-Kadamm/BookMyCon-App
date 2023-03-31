import { CardElement } from "@stripe/react-stripe-js"
import axios from "axios"
import React, { useState, useEffect } from 'react'
import '../../../css/Stripe.css'
import { useDispatch, useSelector } from 'react-redux';
import { ReactSession } from "react-client-session";
import { toast } from "react-toastify";
import CurrencyRupeeIcon from '@mui/icons-material/CurrencyRupee';

const CARD_OPTIONS = {
    iconStyle: "solid",
    style: {
        base: {
            iconColor: "#c4f0ff",
            color: "#fff",
            fontWeight: 500,
            fontFamily: "Roboto, Open Sans, Segoe UI, sans-serif",
            fontSize: "16px",
            fontSmoothing: "antialiased",
            ":-webkit-autofill": { color: "#fce883" },
            "::placeholder": { color: "#87bbfd" }
        },
        invalid: {
            iconColor: "#ffc7ee",
            color: "#ffc7ee"
        }
    }
}


export default function PaymentForm() {

    const cart = useSelector((state) => state);
    const [success] = useState(false)
    const dispatch = useDispatch();
    const add = (previousValue, currentValue) => {
        return previousValue + currentValue.productPrice * currentValue.quantity
    }
    const cartTotal = cart.reduce(add, 0);
    const [orderContent, setOrderContent] = useState({
        productList: cart,
        user: {},
        total: cartTotal,
    })
    const [setShow] = useState(false)

    useEffect(() => {
        fetchUser();
    }, [])

    useEffect(() => {
        setOrderContent((prevState) => { return { ...prevState, productList: cart, total: cartTotal } });
    }, [cart]);

    const fetchUser = () => {
        axios
            .get(
                "http://localhost:8080/user/get-user-by-email/" +
                JSON.parse(sessionStorage.getItem("userLogin")).userEmail
            )
            .then((response) => {
                setOrderContent({ ...orderContent, user: response.data });
            });
    };
    const placeOrder = () => {
        dispatch({ type: "EMPTY", payload: [] })
        axios.post("http://localhost:8080/order/place-order/" + ReactSession.get("BookingIdForFood"), orderContent).
            then((response) => {
                sessionStorage.setItem('total', orderContent.total)
                setShow(true)

            })
        toast.success('Transaction Successful')


    }

    return (

        <div className="div">
            {!success ?
                <form >
                    <fieldset className="FormGroup">
                        <div className="FormRow">
                            <CardElement options={CARD_OPTIONS} />
                        </div>
                    </fieldset>
                    <button onClick={placeOrder}>Pay  <CurrencyRupeeIcon fontSize="small" ></CurrencyRupeeIcon>{orderContent.total}</button>
                </form>
                :
                <div>
                    <h2>You just bought a sweet spatula congrats this is the best decision of you're life</h2>
                </div>
            }

        </div>
    )
}