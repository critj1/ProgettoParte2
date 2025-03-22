import matplotlib.pyplot as plt
import polars as ps
import sys


# Load the data from the file passed as an argument
def load_data(file_path: str):
    data = ps.read_csv(file_path, separator=";", has_header=False,
                       new_columns=["timestamp", "curr_bank_money", "curr_wallet_money"], try_parse_dates=True)
    return data


# Plot the data
def plot_quantity_changes(data):
    plt.figure(figsize=(10, 6))
    plt.plot(data['timestamp'], data['curr_bank_money'], marker='o', linestyle='-', color='b',
             label="Bank account trend")
    plt.plot(data['timestamp'], data['curr_wallet_money'], marker='h', linestyle='--', color='g', label="Wallet trend")
    plt.legend()
    plt.title('Money Changes Over Time')
    plt.xlabel('Timestamp')
    plt.ylabel('Money')
    plt.xticks(rotation=45)
    plt.grid(True)
    plt.tight_layout()
    plt.show()


if __name__ == "__main__":
    if len(sys.argv) < 2:
        sys.exit(1)

    # Get the file path from the command-line argument
    file_path = sys.argv[1]
    data = load_data(file_path)
    plot_quantity_changes(data)
