import { useState } from "react";
import { addVehicle, Vehicle } from "../../services/VehicleService";

const VehicleForm = () => {
  const [state, setState] = useState<"error" | "pending" | "success">(
    "pending"
  );
  const [form, setForm] = useState<Vehicle>({
    licensePlate: "",
    model: "",
    color: "",
    chassisNumber: "",
  });

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setForm((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    console.log(form);

    try {
      await addVehicle({ ...form });
      setState("success");
    } catch (err) {
      console.error("Failed to submit vehicle", err);
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
          <label htmlFor="licensePlate" className="text-xl field">
            License Plate
          </label>
          <input
            type="text"
            id="licensePlate"
            name="licensePlate"
            placeholder="License Plate"
            value={form.licensePlate}
            onChange={handleChange}
            className="box"
            required
          />

          <label htmlFor="model" className="text-xl field">
            Model
          </label>
          <input
            type="text"
            id="model"
            name="model"
            placeholder="Model"
            value={form.model}
            onChange={handleChange}
            className="box"
            required
          />

          <label htmlFor="color" className="text-xl field">
            Color
          </label>
          <input
            type="text"
            id="color"
            name="color"
            placeholder="Color"
            value={form.color}
            onChange={handleChange}
            className="box"
            required
          />

          <label htmlFor="chassisNumber" className="text-xl field">
            Chassis Number
          </label>
          <input
            type="text"
            id="chassisNumber"
            name="chassisNumber"
            placeholder="Chassis Number"
            value={form.chassisNumber}
            onChange={handleChange}
            className="box"
            required
          />

          <button type="submit" className="dark-box p-0 mt-4">
            Save Vehicle
          </button>
        </>
      )}
      {state === "success" && (
        <div className="bg-green-300 flex rounded-lg p-4 mt-8">
          <p className="text-white">You have successfully added a Vehicle</p>
          <svg className="plus-icon size-7 text-muted flex items-center justify-center">
            <use xlinkHref="/assets/icons.svg#tick"></use>
          </svg>
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

export default VehicleForm;
