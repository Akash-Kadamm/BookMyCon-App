import React, { useState } from 'react'
import RazorpayPayment from './RazorpayPayment';

const PaymentGateway = () => {
  const [checkout, setCheckOut] = useState(false);

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
}

export default PaymentGateway