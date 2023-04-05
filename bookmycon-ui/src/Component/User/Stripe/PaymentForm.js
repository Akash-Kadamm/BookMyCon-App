import { CardElement, useElements, useStripe } from "@stripe/react-stripe-js"
import axios from "axios"
import React, { useState, useEffect } from 'react'
import '../../../css/Stripe.css'
import { useDispatch, useSelector } from 'react-redux';
import { useNavigate } from 'react-router-dom';
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
    const [success, setSuccess] = useState(false)
    const [disabled, setDisabled] = useState(true);
    const stripe = useStripe()
    const elements = useElements()
    const navigate = useNavigate()

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
    const [modalOpen, setModalOpen] = useState(false);
    const [show, setShow] = useState(false)
    const hideModal = () => {
        setShow(false)
    }
    const emptyCart = <img className="w-75" src="https://www.vinsolutions.com/wp-content/uploads/sites/2/vinsolutions/media/Vin-Images/news-blog/Empty_Shopping_Cart_blog.jpg" alt="" />
    useEffect(() => {
        fetchUser();
    }, [])

    useEffect(() => {
        setOrderContent((prevState) => { return { ...prevState, productList: cart, total: cartTotal } });
    }, [cart]);

    const handleDisabledButton = (e) => {

    }
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

    //fetch order id to show transaction id 
    const fetchOrderId = () => {
        const response = axios
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

                toast.success('Transaction Successful')
                navigate("/")

            })



    }

    const handleChange = (e) => {
        if (e.target.value.length >= 6) {
            setDisabled(false)
        } else {
            setDisabled(true)
        }
    }

    const handleSubmit = async (e) => {
        e.preventDefault()
        const { error, paymentMethod } = await stripe.createPaymentMethod({
            type: "card",
            card: elements.getElement(CardElement)
        })


        if (!error) {
            try {
                const { id } = paymentMethod
                dispatch({ type: "EMPTY", payload: [] })
                const response = await axios.post("http://localhost:8080/order/place-order/" + ReactSession.get("BookingIdForFood"), orderContent, {
                    amount: orderContent.total,
                    id,

                })
                console.log(response)
                console.log(response.status)
                if (response.status === 200) {
                    console.log("Successful payment")
                    toast.success('Transaction Successful')
                    setSuccess(true)
                }



            } catch (error) {
                toast.error('Your card number is incomplete."')
                console.log("Error", error)

            }
        } else {
            console.log(error.message)
            toast.error('Your card number is incomplete."')
            //navigate("/")

        }
    }

    return (

        <div className="div">
            {!success ?
                <form onSubmit={handleSubmit}>
                    <fieldset className="FormGroup">
                        <div className="FormRow">
                            <CardElement options={CARD_OPTIONS} onChange={handleChange} />
                        </div>
                    </fieldset>
                    <button className="StripeButton" >Pay  <CurrencyRupeeIcon fontSize="small" ></CurrencyRupeeIcon>{orderContent.total}</button>

                    {/* <button className="StripeButton" disabled={disabled} onClick={placeOrder} >Pay  <CurrencyRupeeIcon fontSize="small" ></CurrencyRupeeIcon>{orderContent.total}</button> */}
                </form>
                :

                <div>
                    <h1>Thank You !!</h1>
                    <h2>Your Order Has Been Received .</h2>
                    <h2>You just bought a sweet spatula congrats this is the best decision of you're life</h2>

                    <button onClick={() => navigate("/")} className="btn btn-primary">Go Home</button>
                </div>
            }

        </div>
    )
}