import React,{useState} from 'react'
import GooglePayButton from '@google-pay/button-react'
import RazorpayPayment from './RazorpayPayment';

const PaymentGateway = () => {
  const [checkout, setCheckOut] = useState(false);


//   const userSignIn = JSON.parse(sessionStorage.getItem("userLogin"))
//   const total = sessionStorage.getItem("total")
//   console.log(total)
//   return (
//     <div>
//         <GooglePayButton
//   environment="TEST"
//   paymentRequest={{
//     apiVersion: 2,
//     apiVersionMinor: 0,
//     allowedPaymentMethods: [
//       {
//         type: 'CARD',
//         parameters: {
//           allowedAuthMethods: ['PAN_ONLY', 'CRYPTOGRAM_3DS'],
//           allowedCardNetworks: ['MASTERCARD', 'VISA'],
//         },
//         tokenizationSpecification: {
//           type: 'PAYMENT_GATEWAY',
//           parameters: {
//             gateway: 'example',
//             gatewayMerchantId: 'exampleGatewayMerchantId',
//           },
//         },
//       },
//     ],
//     merchantInfo: {
//       merchantId: '12345678901234567890',
//       merchantName: 'Demo Merchant',
//     },
//     transactionInfo: {
//       totalPriceStatus: 'FINAL',
//       totalPriceLabel: 'Total',
//       totalPrice: total,
//       currencyCode: 'INR',
//       countryCode: 'IN',
//     },
//   }}
//   onLoadPaymentData={paymentRequest => {
//     console.log('load payment data', paymentRequest);
//   }}
// />

return (
  <div>
    {checkout ? (
      <RazorpayPayment />
    ) : (
      <button
        onClick={() => {
          setCheckOut(true);
        }}
      >
        Checkout
      </button>
    )}
  </div>
);

    // </div>
  //)
}

export default PaymentGateway