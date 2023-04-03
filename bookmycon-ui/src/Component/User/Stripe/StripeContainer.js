
import { Elements } from "@stripe/react-stripe-js"
import { loadStripe } from "@stripe/stripe-js"
import React from "react"
import PaymentForm from "./PaymentForm"
import '../../../css/Stripe.css'

const PUBLIC_KEY = "pk_test_51MgLyySHRThIIfgMfApqjae2Jc2cOXVdpPxIwzwbaVeVf6qwlwF0zLDgAkObBNyCxG43lVuwhKZWjQQOzPb9YG7J00zx74ReGS"

const stripeTestPromise = loadStripe(PUBLIC_KEY)

export default function StripeContainer() {
	return (
		<Elements stripe={stripeTestPromise}>
			<PaymentForm />
		</Elements>
	)
}