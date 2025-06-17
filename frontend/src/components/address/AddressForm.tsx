import { useState } from "react";
import { addAddress, Address } from "../../services/AddressService";

const AddressForm = () => {
  const [state, setState] = useState<"error" | "pending" | "success">(
    "pending"
  );
  const [form, setForm] = useState<Address>({
    city: "",
    street: "",
    zip: "",
    country: "",
    number: 0,
    type: "home",
  });

  const handleChange = (
    e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>
  ) => {
    const { name, value } = e.target;
    setForm((prev) => ({
      ...prev,
      [name]: name === "number" ? parseInt(value, 10) : value,
    }));
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    console.log(form);

    try {
      await addAddress(form);
      setState("success");
    } catch (err) {
      console.error("Failed to submit address", err);
      setState("error");
    }
  };

  return (
    <form
      onSubmit={handleSubmit}
      className="p-4 flex flex-col gap-2 bg-white rounded-md max-w-md"
    >
      {state !== "success" && (
        <>
          <label htmlFor="street" className=" text-xl field">
            Street
          </label>
          <input
            type="text"
            id="street"
            name="street"
            placeholder="Street"
            value={form.street}
            onChange={handleChange}
            className="box"
            required
          />
          <label htmlFor="door" className=" text-xl field">
            Door number
          </label>
          <input
            type="number"
            id="door"
            name="number"
            placeholder="Number"
            value={form.number}
            onChange={handleChange}
            className="box"
            required
          />
          <label htmlFor="city" className=" text-xl field">
            City
          </label>
          <input
            type="text"
            id="city"
            name="city"
            placeholder="City"
            value={form.city}
            onChange={handleChange}
            className="box"
            required
          />
          <label htmlFor="zip" className=" text-xl field">
            ZIP code
          </label>
          <input
            id="zip"
            type="text"
            name="zip"
            placeholder="ZIP Code"
            value={form.zip}
            onChange={handleChange}
            className="box"
            required
          />
          <label htmlFor="country" className=" text-xl field">
            Country
          </label>
          <input
            id="country"
            type="text"
            name="country"
            placeholder="Country"
            value={form.country}
            onChange={handleChange}
            className="box"
            required
          />
          <button type="submit" className="dark-box p-0">
            Save Address
          </button>
        </>
      )}
      {state === "success" && (
        <div className="bg-green-300 flex rounded-lg p-4 mt-8">
          <svg className="plus-icon size-7 text-muted flex items-center justify-center">
            <use xlinkHref="assets/icons.svg#tick"></use>
          </svg>
          <p className="text-white">You have added an Address</p>
        </div>
      )}
      {state === "error" && (
        <p className="error bottom-0 left-1/2 -translate-x-1/2">
          Please try again later
        </p>
      )}
    </form>
  );
};

export default AddressForm;
