import React, { useRef ,useEffect} from "react";  





// AcGjmcLR1n55vmMilshq0y67Z7oYeOBpqbMJNhdWetZ6NwOyniM_0nhyCpAdz1NqZIH0Xu9TKZCComrA
//user mail :sb-43tj47925199110@business.example.com
//pass: Paypal@123
const PaypalPayment = () => {
    const paypal = useRef()
    const total = sessionStorage.getItem("total")
    useEffect(() => {
        window.paypal
          .Buttons({
            createOrder: (data, actions, err) => {
              return actions.order.create({
                intent: "CAPTURE",
                purchase_units: [
                  {
                    //description: "Cool looking table",
                    amount: {
                      currency_code: "CAD",
                      value: total,
                    },
                  },
                ],
              });
            },
            onApprove: async (data, actions) => {
              const order = await actions.order.capture();
              console.log(order);
            },
            onError: (err) => {
              console.log(err);
            },
          })
          .render(paypal.current);
      }, []);
    
  return (
    <div>
        <div ref={paypal}></div>
    </div>
  )
}

export default PaypalPayment