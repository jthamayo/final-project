import { useState } from "react";
import { addJob, JobWithAddress } from "../../services/JobService";

const JobForm = () => {
  const [state, setState] = useState<"error" | "pending" | "success">(
    "pending"
  );

  const [form, setForm] = useState<JobWithAddress>({
    job: {
      isIrregular: false,
      isNocturnal: false,
    },
    address: {
      city: "",
      street: "",
      zip: "",
      country: "",
      number: 0,
      type: "work",
    },
  });

  const handleChange = (
    e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>
  ) => {
    const { name, value, type, checked } = e.target;

    if (["city", "street", "zip", "country", "number", "type"].includes(name)) {
      setForm((prev) => ({
        ...prev,
        address: {
          ...prev.address,
          [name]: name === "number" ? parseInt(value, 10) : value,
        },
      }));
    } else {
      setForm((prev) => ({
        ...prev,
        [name]: type === "checkbox" ? checked : value,
      }));
    }
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      await addJob(form);
      setState("success");
    } catch (err) {
      console.error("Failed to submit job", err);
      setState("error");
    }
  };

  return (
    <form
      onSubmit={handleSubmit}
      className="p-4 flex flex-col gap-4 bg-white rounded-md w-full"
    >
      {state !== "success" && (
        <>
          <fieldset className="grid grid-cols-1 md:grid-cols-2 gap-4">
            <div>
              <label htmlFor="street" className="text-xl field">
                Street
              </label>
              <input
                type="text"
                id="street"
                name="street"
                value={form.address.street}
                onChange={handleChange}
                className="box"
                placeholder="Street"
                required
              />
            </div>

            <div>
              <label htmlFor="number" className="text-xl field">
                Door Number
              </label>
              <input
                type="number"
                id="number"
                name="number"
                value={form.address.number}
                onChange={handleChange}
                className="box"
                placeholder="Number"
                required
              />
            </div>

            <div>
              <label htmlFor="city" className="text-xl field">
                City
              </label>
              <input
                type="text"
                id="city"
                name="city"
                value={form.address.city}
                onChange={handleChange}
                className="box"
                required
              />
            </div>

            <div>
              <label htmlFor="zip" className="text-xl field">
                ZIP Code
              </label>
              <input
                type="text"
                id="zip"
                name="zip"
                value={form.address.zip}
                onChange={handleChange}
                className="box"
                required
              />
            </div>
            <div className="flex flex-col justify-center">
              <label className="text-xl field">
                <input
                  type="checkbox"
                  name="isIrregular"
                  checked={form.job.isIrregular}
                  onChange={handleChange}
                  className="mr-2"
                />
                Irregular hours
              </label>
              <label className="text-xl field">
                <input
                  type="checkbox"
                  name="isNocturnal"
                  checked={form.job.isNocturnal}
                  onChange={handleChange}
                  className="mr-2"
                />
                Nocturnal job
              </label>
            </div>
            <div>
              <label htmlFor="country" className="text-xl field">
                Country
              </label>
              <input
                type="text"
                id="country"
                name="country"
                value={form.address.country}
                onChange={handleChange}
                className="box"
                required
              />
            </div>
          </fieldset>

          <button type="submit" className="dark-box mt-2 p-0">
            Save Job
          </button>
        </>
      )}

      {state === "success" && (
        <div className="bg-green-300 flex rounded-lg p-4 mt-8">
          <svg className="plus-icon size-7 text-muted flex items-center justify-center">
            <use xlinkHref="/assets/icons.svg#tick"></use>
          </svg>
          <p className="text-white">You have added a Job</p>
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

export default JobForm;
